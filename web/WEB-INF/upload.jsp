<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Upload</title>
    </head>
    <body>
        <form action="/thumbsup/upload" method="POST"
              enctype="multipart/form-data">
            <input type="file" name="image" />

            <br />
            <input type="submit" value="Upload"></input>
        </form>

    </body>
</html>
