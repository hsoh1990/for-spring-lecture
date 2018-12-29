package com.ex.wellstone.forblog.events;

import com.ex.wellstone.forblog.common.ErrorsResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EventValidator eventValidator;

    @PostMapping()
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {

        if (errors.hasErrors()) {
            return badRequest(errors);
        }

        eventValidator.validate(eventDto, errors);
        if (errors.hasErrors()) {
            return badRequest(errors);
        }


        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = eventRepository.save(event);

        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
        URI createUri = selfLinkBuilder.toUri();

        EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));
        eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));

        return ResponseEntity.created(createUri).body(eventResource);
    }

    @GetMapping
    public ResponseEntity getEvents(Pageable pageable, PagedResourcesAssembler<Event> assembler) {
        final Page<Event> page = this.eventRepository.findAll(pageable);
        final PagedResources<Resource<Event>> pagedResources = assembler.toResource(page, e -> new EventResource(e));

        pagedResources.add(new Link("/docs/index.html#resources-events-list").withRel("profile"));

        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getEvent(@PathVariable Integer id) {
        final Optional<Event> optionalEvent = this.eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event event = optionalEvent.get();
        final EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(linkTo(EventController.class).slash(event.getId()).withRel("update-event"));
        eventResource.add(new Link("/docs/index.html#resources-events-get").withRel("profile"));

        return ResponseEntity.ok().body(eventResource);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id,
                                      @RequestBody @Valid EventDto eventDto, Errors errors) {
        final Optional<Event> optionalEvent = this.eventRepository.findById(id);
        if(optionalEvent.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(errors.hasErrors()){
            return this.badRequest(errors);
        }

        this.eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()){
            return this.badRequest(errors);
        }

        Event existingEvent = optionalEvent.get();
        modelMapper.map(eventDto, existingEvent);

        Event updateEvent = this.eventRepository.save(existingEvent);
        EventResource eventResource = new EventResource(updateEvent);
        eventResource.add(new Link("/docs/index.html#resources-events-update").withRel("profile"));

        return ResponseEntity.ok(eventResource);
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }
}
