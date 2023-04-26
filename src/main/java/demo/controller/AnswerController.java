package demo.controller;


import demo.entity.AnswerEntity;
import org.springframework.web.bind.annotation.*;
import demo.repository.AnswerRepository;
import demo.repository.QuestionRepository;
import demo.resource.AnswerResource;
import demo.resource.QuestionResource;

import java.util.Arrays;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerController(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    AnswerResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(answerRepository.select()).map(answerEntity ->
                {
                    AnswerResource resource = new AnswerResource(answerEntity);
                    if (expand != null)
                        resource.setQuestionResource(
                                Arrays.stream(questionRepository.selectBySourceId(answerEntity.getId()))
                                        .map(e -> new QuestionResource(e))
                                        .toArray(QuestionResource[]::new)
                        );
                    return resource;
                })
                .toArray(AnswerResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AnswerResource get(@PathVariable Integer id,
                      @RequestParam(required = false) Object expand) {
        AnswerEntity entity = answerRepository.select(id);
        if (entity == null) return null;
        AnswerResource resource = new AnswerResource(entity);
        if (expand != null)
            resource.setQuestionResource(
                    Arrays.stream(questionRepository.selectBySourceId(entity.getId()))
                            .map(e -> new QuestionResource(e))
                            .toArray(QuestionResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    AnswerResource post(@RequestBody AnswerResource resource) {
        AnswerEntity entity = answerRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new AnswerResource(entity);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AnswerResource put(@PathVariable Integer id,
                      @RequestBody AnswerResource resource) {
        AnswerEntity entity = answerRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new AnswerResource(entity);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AnswerResource delete(@PathVariable Integer id) {
        AnswerEntity entity = answerRepository.delete(id);
        if (entity == null) return null;
        return new AnswerResource(entity);
    }
}
