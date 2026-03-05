package com.vod.controller;

import com.vod.dto.ApiResponse;
import com.vod.dto.PlayResponseDto;
import com.vod.dto.ProcessRequestDto;
import com.vod.dto.UploadRequestDto;
import com.vod.dto.UploadResponseDto;
import com.vod.dto.VideoDto;
import com.vod.service.VideoService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // Create presigned post policy
    @PostMapping("/presigned-policy")
    public ApiResponse<UploadResponseDto> generatePresignedPolicy(@RequestBody UploadRequestDto requestDto) {
        var response = videoService.createUploadPolicy(requestDto);

        return ApiResponse.ok(response);
    }

    // minio notify java
    @PostMapping("/webhook")
    public ApiResponse<Void> processVideoPipeline(@RequestBody ProcessRequestDto processRequestDto) {
        var video = VideoDto.from(processRequestDto);

        videoService.processVideoPipeline(video);

        return ApiResponse.ok();
    }

    // Get play url
    @GetMapping("/play/{videoId}")
    public ApiResponse<PlayResponseDto> generatePlayUrl(@PathVariable String videoId) {
        var response = videoService.generatePlayUrl(videoId);

        return ApiResponse.ok(response);
    }
}