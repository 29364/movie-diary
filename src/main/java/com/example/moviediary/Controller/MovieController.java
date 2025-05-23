package com.example.moviediary.Controller;

import com.example.moviediary.DTO.MovieForm;
import com.example.moviediary.Entity.Movie;
import com.example.moviediary.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    MovieRepository movieRepository;
    
    @GetMapping
    public String listMovies(Model model) { // 조회 페이지

        // 1. 모든 데이터 가져오기
        List<Movie> movieEntityList = movieRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("movieList", movieEntityList);

        // 3. 뷰 페이지 설정하기
        return "movies/list";
    }

    @GetMapping("/{id}") // 상세 페이지
    public String showMovie(@PathVariable("id") Long id, Model model) {
        // 1. id를 조회하여 데이터 가져오기
        Movie movieEntity = movieRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("movie", movieEntity);

        // 3. 뷰 페이지 반환하기
        return "movies/detail";
    }

    @GetMapping("/new") // 등록 페이지
    public String createMovie() {
        return "movies/form";
    }

    @PostMapping("/create") // 등록 처리
    public String addMovie(MovieForm form) {

        // 1. DTO를 엔티티로 변환
        Movie movie = form.toEntity();

        // 2. 리포지토리를 이용해 DB에 저장
        Movie saved = movieRepository.save(movie);

        // 3. 저장된 결과의 id로 리다이렉트
        return "redirect:/movies/" + saved.getId();
    }

    @GetMapping("/{id}/edit") // 수정 페이지
    public String editMovie() {
        return "";
    }

    @PostMapping("/{id}/edit") // 수정 처리
    public String updateMovie() {
        return "";
    }
    
    @GetMapping("/{id}/delete") // 삭제
    public String deleteMovie() {
        return "";
    }
}
