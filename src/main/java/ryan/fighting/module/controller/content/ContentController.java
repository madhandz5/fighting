package ryan.fighting.module.controller.content;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/content")
public class ContentController {

    @GetMapping(value="/index")
    public void getIndex() {

    }


}
