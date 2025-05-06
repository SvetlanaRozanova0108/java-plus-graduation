package ru.practicum.event.service.compilation.mapper;

import lombok.experimental.UtilityClass;
import main.java.api.dto.compilation.CompilationDto;
import main.java.api.dto.compilation.NewCompilationDto;
import ru.practicum.event.service.compilation.model.Compilation;
import ru.practicum.event.service.event.model.Event;
import main.java.api.dto.event.EventShortDto;

import java.util.HashSet;
import java.util.List;

@UtilityClass
public class CompilationMapper {

    public Compilation toCompilation(NewCompilationDto compilationDto, List<Event> events) {
        return Compilation.builder()
                .pinned(compilationDto.getPinned())
                .title(compilationDto.getTitle())
                .events(new HashSet<>(events))
                .build();
    }

    public CompilationDto toCompilationDto(Compilation compilation, List<EventShortDto> listEventDto) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(listEventDto)
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();


    }
}
