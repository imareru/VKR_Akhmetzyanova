package demo.controller;

import demo.entity.TestEntity;
import org.springframework.web.bind.annotation.*;
import demo.repository.SubjectRepository;
import demo.repository.TestRepository;
import demo.resource.SubjectResource;
import demo.resource.TestResource;

import java.util.Arrays;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TestRepository testRepository;
    private final SubjectRepository subjectRepository;


    public TestController(TestRepository testRepository, SubjectRepository subjectRepository) {
        this.testRepository = testRepository;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    TestResource[] getAll(@RequestParam(required = false) Integer sourceId,
                          @RequestParam(required = false) Object expand) {
        TestEntity[] entities = sourceId == null ?
                testRepository.select() :
                testRepository.selectBySourceId(sourceId);
        return Arrays.stream(entities)
                .map(entity -> {
                    TestResource resource = new TestResource(entity);
                    if (expand != null)
                        resource.setSubjectResource(new SubjectResource(subjectRepository.select(entity.getSubject_id())));
                    return resource;
                })
                .toArray(TestResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        TestEntity entity = testRepository.select(id);
        if (entity == null) return null;
        TestResource resource = new TestResource(entity);
        if (expand != null)
            resource.setSubjectResource(
                    new SubjectResource(subjectRepository.select(entity.getSubject_id()))
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    TestResource post(@RequestBody TestResource resource) {
        TestEntity entity = testRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new TestResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TestResource put(@PathVariable Integer id,
                        @RequestBody TestResource resource) {
        TestEntity entity = testRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new TestResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    TestResource delete(@PathVariable Integer id) {
        TestEntity entity = testRepository.delete(id);
        if (entity == null) return null;
        TestResource resource = new TestResource(entity);
        return resource;
    }
}
