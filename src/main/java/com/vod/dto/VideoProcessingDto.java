package com.vod.dto;

public record VideoProcessingDto(
    String id,
    String name,
    String url
) {
    public static VideoProcessingDto of(String id, String name, String url) {
        return new VideoProcessingDto(id, name, url);
    }
}
