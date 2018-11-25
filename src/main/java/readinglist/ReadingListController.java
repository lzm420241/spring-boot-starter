package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }
    @RequestMapping(value="/{reader}", method = RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader, Model model) {
        System.out.println("get: reader");
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList( @PathVariable("reader") String reader, Book book) {
        System.out.println("post: reader");
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

}
