package thumbsup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ImageManager extends DBManager {

    private final DataSource dataSource;

    public ImageManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private ImageInfo imageFromResultSet(ResultSet resultSet) throws SQLException {
        return new ImageInfo(resultSet.getInt("id"),
                resultSet.getString("filename"),
                resultSet.getString("content_type"));
    }

    public ImageInfo imageInfoBy(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT id, filename, content_type FROM images where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return imageFromResultSet(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return null;
    }

    private void copyIO(InputStream contentStream, OutputStream outputStream) throws IOException {
        try {
            byte[] buffer = new byte[32 * 1024];

            while (true) {
                int numread = contentStream.read(buffer);
                if (numread < 1) {
                    break;
                }
                outputStream.write(buffer, 0, numread);
            }
        } finally {
            contentStream.close();
        }
    }

    public boolean copyImageContent(int id, OutputStream out) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT content FROM images where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                copyIO(resultSet.getBinaryStream("content"),out);
            }

        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return false;
    }

    public List<ImageInfo> allImages() {
        List<ImageInfo> images = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select id, filename, content_type from Images");

            while (resultSet.next()) {
                images.add(imageFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return images;
    }

    boolean saveImage(String fileName, String contentType, InputStream inputStream) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO images (filename, content_type, content) values (?, ?, ?)");
            statement.setString(1, fileName);
            statement.setString(2, contentType);
            statement.setBinaryStream(3, inputStream);

            statement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return false;
    }
}