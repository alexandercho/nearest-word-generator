package com.example.springboot;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class NearestWordGeneratorController {
    NearestWordGenerator nwg;
    @RequestMapping("/")
    public String index() {
        if (nwg == null) {
            this.nwg = new NearestWordGenerator();
            this.nwg.generateDictionary("words.txt", false);
        }
        return "Nearest Word Generator created with default dictionary";
    }

    @PutMapping("/reformat")
    public String useNewDictionary(@RequestParam(value = "link") String link) {
        this.nwg = new NearestWordGenerator();
        Boolean success = this.nwg.generateDictionary(link, true);
        return success ? "New dictionary updated" : "Dictionary failed to load. Default dictionary was loaded instead";
    }

    @GetMapping("/generate")
    public String[] generateWords(@RequestParam(value = "word") String word,
                                  @RequestParam(value = "delta") String delta,
                                  @RequestParam(value = "number") String number) {
        return nwg.generateWord(word, Integer.parseInt(delta), Integer.parseInt(number));
    }
}
