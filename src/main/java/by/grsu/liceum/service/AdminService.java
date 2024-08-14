package by.grsu.liceum.service;

import by.grsu.liceum.dto.admin.RatingDto;

public interface AdminService {
    void addRating(RatingDto ratingDto);
    void getRating(RatingDto ratingDto);
    //todo activity_type, activity
}
