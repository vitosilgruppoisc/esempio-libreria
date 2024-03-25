package silecchia.test.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import silecchia.test.model.LibroModel;
import silecchia.test.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/libros")
@RequiredArgsConstructor
public class BookController {

    public static final String USER_LOGIN = "userLogin";
    @Autowired
    private  LibroService libroService;

    @GetMapping
    public String getLibroPage(Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        model.addAttribute(USER_LOGIN, username);

        List<LibroModel> libros = libroService.getAllBooksByLogin(username);

        model.addAttribute("userLibros", libros);
        return "libro_page";
    }

    @GetMapping("/create")
    public String getCreateLibroPage(Model model) {
        model.addAttribute("newLibro", new LibroModel());
        return "create_libro_page";
    }

    @PostMapping("/createLibro")
    public String createLibro(@ModelAttribute LibroModel libro) {
        libroService.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/edit/{title}")
    public String getEditLibroPage(Model model, @PathVariable String title) {
        LibroModel byTitle = libroService.findByTitleAndDelete(title);
        model.addAttribute("libroToEdit", byTitle);
        return "edit_libro_page";
    }

    @PostMapping("/editLibro")
    public String editLibro(@ModelAttribute LibroModel libro) {
        libroService.edit(libro);
        return "redirect:/libros";
    }

    @GetMapping("/delete/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String title) {
        libroService.delete(title);
        return "redirect:/libros";
    }
}
