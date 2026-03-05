package com.vod.dto;

public record UploadResponseDto(
    String uploadUrl,
    String videoName,
    String contentType,
    String policy,
    String xAmzAlgorithm,
    String xAmzCredential,
    String xAmzDate,
    String xAmzSignature
) {
    public static UploadResponseDto of(String uploadUrl, String videoName, String contentType, PresignedPostFormDataDto formData) {
        return new UploadResponseDto(
            uploadUrl, 
            videoName,
            contentType,
            formData.policy(),
            formData.xAmzAlgorithm(),
            formData.xAmzCredential(),
            formData.xAmzDate(),
            formData.xAmzSignature()
        );
    }
}