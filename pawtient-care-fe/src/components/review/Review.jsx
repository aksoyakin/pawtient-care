import React from 'react';
import {UserType} from "../utils/utilities.js";
import UserImage from "../common/UserImage.jsx";
import RatingStars from "../rating/RatingStars.jsx";

const Review = ({review, userType}) => {

    const displayName =
        userType === UserType.PATIENT
            ? `You rated Dr. ${review.veterinarianName}`
            : `Reviewed by: ${review.patientName}`;

    return (
        <div className={"mb-4"}>
            <div className={"d-flex align-items-center me-5"}>
                {userType === UserType.VET ? (
                    <UserImage
                        userId={review.patientId}
                        userPhoto={review.patientImage}
                    />
                ) : (
                    <UserImage
                        userId={review.veterinarianId}
                        userPhoto={review.veterinarianImage}
                    />
                )}

                <div>
                    <div>
                        <h5 className={"title ms-3"}>
                            <RatingStars
                                rating={review.stars}
                            />
                        </h5>
                        <div className={"mt-4"}>
                            <p className={"review-text ms-4"}>
                                {review.feedback}
                            </p>
                        </div>
                        <div>
                            <p className={"ms-4"}>
                                {displayName}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
        </div>
    );
};

export default Review;