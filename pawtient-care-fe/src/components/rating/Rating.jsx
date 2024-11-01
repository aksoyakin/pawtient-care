import React, {useState} from 'react';
import UseMessageAlerts from "../hooks/UseMessageAlerts.js";
import {Button, Form, FormCheck, FormControl, FormLabel} from "react-bootstrap";
import {FaStar} from "react-icons/fa";
import AlertMessage from "../common/AlertMessage.jsx";
import {addReview} from "../review/ReviewService.jsx";
import {useParams} from "react-router-dom";

const Rating = ({veterinarianId, onReviewSubmit}) => {

    const [hover, setHover] = useState(null);
    const [rating, setRating] = useState(null);
    const [feedback, setFeedback] = useState("");

    const {
        successMessage,
        errorMessage,
        setSuccessMessage,
        setErrorMessage,
        showSuccessAlert,
        showErrorAlert,
        setShowSuccessAlert,
        setShowErrorAlert,
    } = UseMessageAlerts();

    const reviewerId = 6;

    const handleRatingChange = (value) => {
        setRating(value);
    }

    const handleFeedbackChange = (e) => {
        setFeedback(e.target.value);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const reviewInfo = {
            stars: rating,
            feedback: feedback,
        };

        try {
            console.log("The review info: ", reviewInfo);
            const response = await addReview(veterinarianId, reviewerId, reviewInfo);
            setSuccessMessage(response.message);
            setShowSuccessAlert(true);

            setFeedback("");
            setRating(null);


            if (onReviewSubmit) {
                onReviewSubmit();
            }
        } catch (error) {
            setErrorMessage(error.message);
            setShowErrorAlert(true);
        }
    }

    return (

        <React.Fragment>

            {showErrorAlert && (
                <AlertMessage
                    type={"danger"}
                    message={errorMessage}
                />
            )}

            {showSuccessAlert && (
                <AlertMessage
                    type={"success"}
                    message={successMessage}
                />
            )}


            <Form>
                <h3>Rate This Doctor : </h3>
                <div className={"mb-2"}>
                    {[...Array(5)].map((_, index) => {
                        const ratingValue = index + 1;
                        return (
                            <FormLabel key={index} className={"me-2"}>
                                <FormCheck
                                    type="radio"
                                    name="rating"
                                    value={ratingValue}
                                    onChange={() => handleRatingChange(ratingValue)}
                                    checked={rating === ratingValue}
                                    inline
                                />
                                <FaStar
                                    size={20}
                                    className={'star'}
                                    color={
                                        ratingValue <= (hover || rating) ? "#ffc107" : "#e4e5e9"
                                    }
                                    onMouseEnter={() => setHover(ratingValue)}
                                    onMouseLeave={() => setHover(null)}
                                />
                            </FormLabel>
                        )
                    })}
                </div>

                <div>
                    <FormControl
                        as="textarea"
                        row={4}
                        value={feedback}
                        required
                        onChange={handleFeedbackChange}
                        placeholder={"Leave a feedback message"}
                    />
                </div>
                <div className={"mt-2"}>
                    <Button type={"submit"} variant="outline-primary" onClick={handleSubmit}> Submit Review </Button>
                </div>
                <p> You have rated this doctor with{" "}
                    <span style={{color: "orange"}}> {rating} stars </span>
                </p>
            </Form>
        </React.Fragment>
    );
};

export default Rating;