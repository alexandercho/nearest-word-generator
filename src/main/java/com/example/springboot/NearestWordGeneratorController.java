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
        if (nwg == null) {
            this.nwg = new NearestWordGenerator();
            this.nwg.generateDictionary("words.txt");
        }
        return "Nearest Word Generator created with default dictionary";
    }

    @GetMapping("/generate")
    public String[] generateWords(@RequestParam(value = "word") String word,
                                  @RequestParam(value = "delta") String delta,
                                  @RequestParam(value = "number") String number) {
        //http://localhost:8080/generate?word=car&delta=1&number=1
        return nwg.generateWord(word, Integer.parseInt(delta), Integer.parseInt(number));
    }
}
