package com.example.demo.repository;

import com.example.demo.entity.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArchiveRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Archive board) throws Exception {
        String query = "insert into archive_board (date, artist, link, social) values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {
                        PreparedStatement ps = con.prepareStatement(query, new String[] {"boardNo"});
                        ps.setString(1, board.getArtist());
                        ps.setString(2, board.getLink());
                        ps.setString(3, board.getSocial());
                        return ps;
                    }
                }, keyHolder);

        board.setBoardNo(keyHolder.getKey().longValue());
    }

    public Archive read(Long boardNo) throws Exception {
        List<Archive> results = jdbcTemplate.query(
                "select board_no, artist, link, social, date " +
                        "from archive_board where board_no = ?",
                new RowMapper<Archive>() {
                    @Override
                    public Archive mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Archive board = new Archive();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setArtist(rs.getString("artist"));
                        board.setLink(rs.getString("link"));
                        board.setSocial(rs.getString("social"));
                        board.setDate(rs.getDate("date"));

                        return board;
                    }
                }, boardNo
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public void update(Archive board) throws Exception {
        String query = "update archive_board set artist = ?, link = ?, where board_no = ?";
        jdbcTemplate.update(query, board.getArtist(), board.getLink(), board.getBoardNo());
    }

    public void delete(Long boardNo) throws Exception {
        String query = "delete from archive_board where board_no = ?";
        jdbcTemplate.update(query, boardNo);
    }

    public List<Archive> list() throws Exception {
        List<Archive> results = jdbcTemplate.query(
                "select board_no, artist, link, social, date from archive_board " +
                        "where board_no > 0 order by board_no desc, date desc",
                new RowMapper<Archive>() {
                    @Override
                    public Archive mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Archive board = new Archive();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setArtist(rs.getString("artist"));
                        board.setSocial(rs.getString("link"));
                        board.setLink(rs.getString("social"));
                        board.setDate(rs.getDate("date"));
                        return board;
                    }
                }
        );

        return results;
    }
}
