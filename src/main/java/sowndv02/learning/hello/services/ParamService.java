package sowndv02.learning.hello.services;

import java.util.Date;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {

    @Autowired // Tìm kiếm trong IoC (Inversion of Control) add vào DI(Dependency Injection)
    HttpServletRequest request; // Cho phép đọc dữ liệu từ người dùng gửi lên

    public String getString(String name, String defaultValue) {

        var value = request.getParameter(name);

        if (value == null)
            return defaultValue;

        return value;
    }

    public int getInt(String name, int defaultValue) {

        var value = request.getParameter(name);

        if (value == null)
            return defaultValue;

        return Integer.parseInt(value);
    }

    public double getDouble(String name, double defaultValue) {

        var value = request.getParameter(name);

        if (value == null)
            return defaultValue;

        return Double.parseDouble(value);
    }

    public boolean getBoolean(String name, boolean defaultValue) {

        var value = request.getParameter(name);

        if (value == null)
            return defaultValue;

        return Boolean.parseBoolean(value);
    }

    public Date getDate(String name, Date defaultValue) throws ParseException {

        var value = request.getParameter(name);

        if (value == null)
            return defaultValue;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse(value);
    }

    public File saveFile(MultipartFile file, String path) throws Exception {
        try {
            File savedFile = new File(request.getRealPath(path) + "/" + file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}
