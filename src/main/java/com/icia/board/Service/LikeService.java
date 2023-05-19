package com.icia.board.Service;

import com.icia.board.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService{
        @Autowired
        private LikeRepository likeRepository;
        public void addLike(int articleId) {
            likeRepository.addLike(articleId);
        }

        public void addDislike(int articleId) {
            likeRepository.addDislike(articleId);
        }

        public void addHate(int articleId) {
             likeRepository.addHate(articleId);
        }
        public void addDisHate(int articleId) {
              likeRepository.addDisHate(articleId);
        }

}

