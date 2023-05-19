package com.icia.board.Controller;

import com.icia.board.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/goodUp")
    public ResponseEntity<String> addLike(@RequestParam("articleId") int articleId) {
        try {
            likeService.addLike(articleId);
            return ResponseEntity.ok("Like added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add like");
        }
    }

    @PostMapping("/goodDown")
    public ResponseEntity<String> addDislike(@RequestParam("articleId") int articleId) {
        try {
            likeService.addDislike(articleId);
            return ResponseEntity.ok("Dislike added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add dislike");
        }
    }
}
