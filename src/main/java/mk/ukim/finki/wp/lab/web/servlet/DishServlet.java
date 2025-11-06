package mk.ukim.finki.wp.lab.web.servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    @Autowired private DishService dishService;
    @Autowired private ChefService chefService;
    @Autowired private SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefIdStr = req.getParameter("chefId");
        if (chefIdStr == null || chefIdStr.isBlank()) {
            resp.sendRedirect("/listChefs");
            return;
        }
        Long chefId = Long.parseLong(chefIdStr);
        renderPage(req, resp, chefId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefIdStr = req.getParameter("chefId");
        if (chefIdStr == null || chefIdStr.isBlank()) {
            resp.sendRedirect("/listChefs");
            return;
        }
        Long chefId = Long.parseLong(chefIdStr);
        renderPage(req, resp, chefId);
    }

    private void renderPage(HttpServletRequest req, HttpServletResponse resp, Long chefId) throws IOException {
        Chef selectedChef = chefService.findById(chefId);

        resp.setContentType("text/html;charset=UTF-8");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chef", selectedChef);
        context.setVariable("dishes", dishService.listDishes());

        springTemplateEngine.process("dishesList.html", context, resp.getWriter());
    }
}
