import com.mycompany.projectx1.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet("/register")
public class RegisterUserController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("mobile");
        String password = request.getParameter("password");

        boolean isRegistered = userService.registerUser(firstName, lastName, username, email, phoneNumber, password);

        if (isRegistered) {
            System.out.println("✅ Registration SUCCESSFUL!");
            response.sendRedirect("pages/register.jsp?success=1"); // Redirect correctly
        } else {
            System.out.println("❌ Registration FAILED!");
            response.sendRedirect("pages/register.jsp?error=1"); // Redirect correctly
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response); // Correct forward path
    }
}
