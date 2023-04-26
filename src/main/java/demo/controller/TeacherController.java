package demo.controller;


import demo.entity.TeacherEntity;
import org.springframework.web.bind.annotation.*;
import demo.repository.SubjectRepository;
import demo.repository.TeacherRepository;
import demo.resource.SubjectResource;
import demo.resource.TeacherResource;

import java.util.Arrays;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public TeacherController(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    TeacherResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(teacherRepository.select()).map(teacherEntity ->
                {
                    TeacherResource resource = new TeacherResource(teacherEntity);
                    if (expand != null)
                        resource.setSubjectResources(
                                Arrays.stream(subjectRepository.selectBySourceId(teacherEntity.getId()))
                                        .map(e -> new SubjectResource(e))
                                        .toArray(SubjectResource[]::new)
                        );
                    return resource;
                })
                .toArray(TeacherResource[]::new);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TeacherResource get(@PathVariable Integer id,
                      @RequestParam(required = false) Object expand) {
        TeacherEntity entity = teacherRepository.select(id);
        if (entity == null) return null;
        TeacherResource resource = new TeacherResource(entity);
        if (expand != null)
            resource.setSubjectResources(
                    Arrays.stream(subjectRepository.selectBySourceId(entity.getId()))
                            .map(e -> new SubjectResource(e))
                            .toArray(SubjectResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    TeacherResource post(@RequestBody TeacherResource resource) {
        TeacherEntity entity = teacherRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new TeacherResource(entity);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TeacherResource put(@PathVariable Integer id,
                      @RequestBody TeacherResource resource) {
        TeacherEntity entity = teacherRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new TeacherResource(entity);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    TeacherResource delete(@PathVariable Integer id) {
        TeacherEntity entity = teacherRepository.delete(id);
        if (entity == null) return null;
        return new TeacherResource(entity);
    }
}
