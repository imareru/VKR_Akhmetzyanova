package demo.controller;

import demo.entity.ResultEntity;
import org.springframework.web.bind.annotation.*;
import demo.repository.ResultRepository;
import demo.repository.StudentRepository;
import demo.repository.TestRepository;
import demo.resource.ResultResource;
import demo.resource.StudentResource;
import demo.resource.TestResource;

import java.util.Arrays;

@RestController
@RequestMapping("/results")
public class ResultController {
    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final TestRepository testRepository;

    public ResultController(ResultRepository resultRepository, StudentRepository studentRepository, TestRepository testRepository) {
        this.resultRepository = resultRepository;
        this.studentRepository = studentRepository;
        this.testRepository = testRepository;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    ResultResource[] getAll(@RequestParam(required = false) Integer sourceId,
                            @RequestParam(required = false) Object expand) {
        ResultEntity[] entities = sourceId == null ?
                resultRepository.select() :
                resultRepository.selectBySourceId(sourceId);
        return Arrays.stream(entities)
                .map(entity -> {
                    ResultResource resource = new ResultResource(entity);
                    if (expand != null) {
                        resource.setStudentResource(new StudentResource(studentRepository.select(entity.getStudent_id())));
                        resource.setTestResource(new TestResource(testRepository.select(entity.getTest_id())));
                    }
                    return resource;
                })
                .toArray(ResultResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResultResource get(@PathVariable Integer id, @RequestParam(required = false) Object expand) {
        ResultEntity entity = resultRepository.select(id);
        if (entity == null) return null;
        ResultResource resource = new ResultResource(entity);
        if (expand != null){
            resource.setStudentResource(new StudentResource(studentRepository.select(entity.getStudent_id())));
            resource.setTestResource(new TestResource(testRepository.select(entity.getTest_id())));
        }
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResultResource post(@RequestBody ResultResource resource) {
        ResultEntity entity = resultRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new ResultResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResultResource put(@PathVariable Integer id,
                      @RequestBody ResultResource resource) {
        ResultEntity entity = resultRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new ResultResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResultResource delete(@PathVariable Integer id) {
        ResultEntity entity = resultRepository.delete(id);
        if (entity == null) return null;
        ResultResource resource = new ResultResource(entity);
        return resource;
    }
}
