package com.vod.dto;

import java.nio.file.Path;

public record VideoDto(
    String id,
    String name
) {
    public static VideoDto from(ProcessRequestDto processRequestDto) {
        String fullKey = processRequestDto.key();

        String videoName = Path.of(fullKey).getFileName().toString();

        int dotIndex = videoName.lastIndexOf('.');
        String videoId = (dotIndex == -1) ? videoName : videoName.substring(0, dotIndex);

        return new VideoDto(videoId, videoName);
    }
}
