package com.vod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vod.entity.Video;
import com.vod.enums.VideoStatus;

import jakarta.transaction.Transactional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Video v SET v.status = :newStatus WHERE v.id = :id AND v.status = :oldStatus")
    int updateStatus(
        @Param("id") String id, 
        @Param("oldStatus") VideoStatus oldStatus, 
        @Param("newStatus") VideoStatus newStatus
    );

    @Modifying
    @Transactional
    @Query("UPDATE Video v SET v.status = com.vod.enums.VideoStatus.FAILED, v.errorMsg = :errorMsg WHERE v.id = :id AND v.status = com.vod.enums.VideoStatus.PROCESSING")
    void markAsFailed(
        @Param("id") String id, 
        @Param("errorMsg") String errorMsg
    );
    
}
