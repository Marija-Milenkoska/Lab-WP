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

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    @Autowired private ChefService chefService;
    @Autowired private DishService dishService;
    @Autowired private SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chefIdStr = req.getParameter("chefId");
        String dishId    = req.getParameter("dishId");

        if (chefIdStr == null || chefIdStr.isBlank() || dishId == null || dishId.isBlank()) {
            resp.sendRedirect("/listChefs");
            return;
        }

        Long chefId = Long.parseLong(chefIdStr);

        Chef updatedChef = chefService.addDishToChef(chefId, dishId);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("chef", updatedChef);
        context.setVariable("dishes", updatedChef.getDishes());

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }
}
