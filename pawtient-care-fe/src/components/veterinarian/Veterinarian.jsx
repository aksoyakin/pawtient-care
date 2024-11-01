import React, {useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import UseMessageAlerts from "../hooks/UseMessageAlerts.js";
import {Card, CardBody, CardText, CardTitle, Col, Container, Row} from "react-bootstrap";
import UserImage from "../common/UserImage.jsx";
import {BsFillArrowRightSquareFill} from "react-icons/bs";
import RatingStars from "../rating/RatingStars.jsx";
import Rating from "../rating/Rating.jsx";
import Review from "../review/Review.jsx";
import {getUserById} from "../user/UserService.jsx";
import AlertMessage from "../common/AlertMessage.jsx";
import review from "../review/Review.jsx";
import Paginator from "../common/Paginator.jsx";

const Veterinarian = () => {

    const [vet, setVet] = useState(null);

    const [isLoading, setIsLoading] = useState(true);

    const {vetId} = useParams();

    const [currentPage, setCurrentPage] = useState(1);

    const [reviewPerPage] = useState(2);

    const {
        errorMessage,
        showErrorAlert,
        setErrorMessage,
        setShowErrorAlert
    } = UseMessageAlerts;

    const getUser = async () => {
        setIsLoading(true);
        try {
            console.log("The vetId: ", vetId)
            const result = await getUserById(vetId);
            console.log("The response: ", result);
            setVet(result.data);
            setIsLoading(false);
        } catch (error) {
            console.log("The error message: ", error)
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true);
            setIsLoading(false);
        }
    };

    useEffect(() => {
        getUser();
    }, [vetId]);

    if (isLoading) {
        return <h1>Loading...</h1>
    }

    const indexOfLastReview = currentPage * reviewPerPage;
    const indexOfFirstReview = indexOfLastReview - reviewPerPage;
    const currentReviews = vet.reviews.slice(
        indexOfFirstReview,
        indexOfLastReview
    ) || [];

    return (
        <Container className={"d-flex justify-content-center align-items-center mt-5"}>
            {showErrorAlert && (
                <AlertMessage type={"danger"} message={errorMessage}/>
            )}
            <Card className="review-card mt-2">
                <Row>
                    <Col>
                        <UserImage
                            userId={vet.id}
                            userPhoto={vet.photo}
                            altText={`${vet.firstName} 's photo`}
                        />
                    </Col>
                    <Col>
                        <Link to={"/doctors"}>
                            <BsFillArrowRightSquareFill/> back to veterinarians
                        </Link>
                    </Col>
                </Row>
                <CardBody>
                    <CardTitle>Dr. {vet.firstName} {vet.lastName}</CardTitle>
                    <CardText>Specialization: {vet.specialization} </CardText>

                    {vet.averageRating > 0 && (
                        <Card.Text className='rating-stars'>
                            Ratings: (
                            {vet.averageRating > 0
                                ? Number(vet.averageRating.toFixed(1))
                                : "0.0"}
                            ) stars
                            <RatingStars rating={vet.averageRating}/> rated by (
                            {vet.totalReviewers || 0}{" "}
                            {vet.totalReviewers === 1 ? "person" : "people"}){" "}
                        </Card.Text>
                    )}

                    <Link
                        to={`/book-appointment/${vet.id}/new-appointment`}
                        className='link'
                    >
                        Book appointment
                    </Link>

                    <hr/>

                    <p className='about'>
                        About Dr. {vet.firstName} {vet.lastName}{" "}
                    </p>

                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime
                        mollitia, molestiae quas vel sint commodi repudiandae consequuntur
                        voluptatum laborum numquam blanditiis harum quisquam eius sed odit
                        fugiat iusto fuga praesentium optio, eaque rerum! Provident
                        similique accusantium nemo autem. Veritatis obcaecati tenetur iure
                        corporis!
                    </p>
                    <hr/>

                    <Rating veterinarianId={vetId} onReviewSubmit={getUser}/>
                    <h4 className='text-center mb-4'>Reviews</h4>
                    <hr/>

                    {/* Render paginated reviews */}
                    {currentReviews && currentReviews.length > 0 ? (
                        currentReviews.map((review) => (
                            <Review
                                key={review.id}
                                review={review}
                                userType={vet.userType}
                            />
                        ))
                    ) : (
                        <p>No reviews available yet.</p>
                    )}

                    <Paginator
                        itemsPerPage={reviewPerPage}
                        totalItems={vet.reviews.length}
                        paginate={setCurrentPage}
                        currentPage={currentPage}
                    >
                    </Paginator>
                </CardBody>
            </Card>

        </Container>
    );
};

export default Veterinarian;