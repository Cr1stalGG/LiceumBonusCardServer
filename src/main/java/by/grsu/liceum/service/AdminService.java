package by.grsu.liceum.service;

import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

public interface AdminService {
    TransactionDto addRating(RatingDto ratingDto);
    TransactionDto getRating(RatingDto ratingDto);
    //todo activity_type, activity
}
