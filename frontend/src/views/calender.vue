<template>
  <Layout>
    <template #content>
      <v-row align="center" justify="center">
        <v-btn icon class="ma-2" @click="$refs.calendar.prev()">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <h1 v-if="$refs.calendar">
          {{ $refs.calendar.title }}
        </h1>
        <v-btn icon class="ma-2" @click="$refs.calendar.next()">
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </v-row>
      <v-row justify="end">
        <v-btn @click.stop="dialog = true" tile mid depressed
          style="padding: 10px; width: 90px; margin: 10px; float: right;">
          <v-icon left>mdi-pencil</v-icon>
          ADD
        </v-btn>
        <v-dialog v-model="dialog" max-width="400">
          <v-card>
            <v-card-title class="headline">Add up the event?</v-card-title>

            <v-card-text> 공연 일정 추가
              <v-text-field id="artist" label="Artist" type="string" />
              <v-menu ref="menu1" v-model="menu1" :close-on-content-click="false"
                max-width="300px" min-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="dateFormatted"
                    label="Date"
                    hint="MM/DD/YYYY"
                    persistent-hint
                    v-bind="attrs"
                    @blur="date = parseDate(dateFormatted)"
                    v-on="on"
                  ></v-text-field>
                </template>
                  <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
              </v-menu>
              <v-select v-model="value" :items="time" label="AM / PM"></v-select>
              <v-select :items="hours" label="Start Hour"></v-select>
              <v-select :items="min" label="Start Minute"></v-select>
              <v-select :items="hours" label="End Hour"></v-select>
              <v-select :items="min" label="End Minute"></v-select>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-3" text @click="submit">ADD</v-btn>
              <v-btn color="blue darken-3" text @click="dialog = false">CANCEL</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
      <v-sheet height="650">
        <v-calendar
          ref="calendar"
          v-model="value"
          type="week"
          :events="events"
          :event-color="getEventColor"
          @change="getEvents"
          >
          <template #day-body="{ date, week }">
            <div
              class="v-current-time"
              :class="{ first: date === week[0].date }"
              :style="{ top: nowY }"
            ></div>
          </template>
          </v-calendar>
      </v-sheet>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import store from '../store'
import { mapState } from 'vuex'
export default {
  components: { Layout },
  computed: {
    ...mapState({
      lists: state => state.lists
    }),
    cal () {
      return this.ready ? this.$refs.calendar : null
    },
    nowY () {
      return this.cal ? this.cal.timeToY(this.cal.times.now) + 'px' : '-10px'
    },
    computedDateFormatted () {
      return this.formatDate(this.date)
    }
  },
  watch: {
    date (val) {
      this.dateFormatted = this.formatDate(this.date)
    }
  },
  data: () => ({
    value: '',
    ready: false,
    events: [],
    dialog: false,
    colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
    names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
    date: new Date().toISOString().substr(0, 10),
    menu1: false,
    time: ['AM', 'PM'],
    hours: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
    min: ['0', '15', '30', '45']
  }),
  mounted () {
    this.ready = true
    this.scrollToTime()
    this.updateTime()
  },
  methods: {
    getEvents ({ start, end }) {
      const events = []
      const min = new Date(`${start.date}T00:00:00`)
      const max = new Date(`${end.date}T23:59:59`)
      const days = (max.getTime() - min.getTime()) / 86400000
      const eventCount = this.rnd(days, days + 20)
      for (let i = 0; i < eventCount; i++) {
        const allDay = this.rnd(0, 3) === 0
        const firstTimestamp = this.rnd(min.getTime(), max.getTime())
        const first = new Date(firstTimestamp - (firstTimestamp % 900000))
        const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
        const second = new Date(first.getTime() + secondTimestamp)
        events.push({
          name: this.names[this.rnd(0, this.names.length - 1)],
          start: first,
          end: second,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: !allDay
        })
      }
      this.events = events
    },
    getEventColor (event) {
      return event.color
    },
    rnd (a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
    add (payload) {
      console.log('add event')
    },
    getCurrentTime () {
      return this.cal ? this.cal.times.now.hour * 60 + this.cal.times.now.minute : 0
    },
    scrollToTime () {
      const time = this.getCurrentTime()
      const first = Math.max(0, time - (time % 30) - 30)
      this.cal.scrollToTime(first)
    },
    updateTime () {
      setInterval(() => this.cal.updateTimes(), 60 * 1000)
    },
    formatDate (date) {
      if (!date) return null
      const [year, month, day] = date.split('-')
      return `${month}/${day}/${year}`
    },
    parseDate (date) {
      if (!date) return null
      const [month, day, year] = date.split('/')
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
    },
    submit () {
      if (this.event.artist === '' || this.event.endDate === '') {
        store.commit('SET_EVENT')
      } else {
        this.$store.dispatch('REQUEST_ADD_EVENT', this.calender)
      }
    }
  }
}
</script>

<style>
  div {
    //border: 2px solid black;
  }
  .v-current-time {
  height: 2px;
  background-color: #ea4335;
  position: absolute;
  left: -1px;
  right: 0;
  pointer-events: none;
  &.first::before {
    content: '';
    position: absolute;
    background-color: #ea4335;
    width: 12px;
    height: 12px;
    border-radius: 50%;
    margin-top: -5px;
    margin-left: -6.5px;
  }
}
</style>
