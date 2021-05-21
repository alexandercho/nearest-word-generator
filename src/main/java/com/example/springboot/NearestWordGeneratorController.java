package com.example.springboot;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class NearestWordGeneratorController {
    NearestWordGenerator nwg;
    @RequestMapping("/")
    public String index() {
        this.nwg = new NearestWordGenerator();
        this.nwg.generateDictionary("word.txt");
        return "Nearest Word Generator created with default dictionary";
    }
    @GetMapping("/generate")
    public String[] generateWords(@RequestParam(value = "word") String word,
                                  @RequestParam(value = "delta") int delta,
                                  @RequestParam(value = "number") int number) {

        return nwg.generateWord(word, delta, number);
    }
}
