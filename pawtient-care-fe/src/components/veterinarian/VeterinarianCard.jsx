import React from 'react';
import {
    Accordion,
    AccordionBody,
    AccordionHeader,
    AccordionItem,
    CardImg,
    CardText,
    CardTitle,
    Col
} from "react-bootstrap";
import {Link} from "react-router-dom";
import placeholder from "../../assets/images/placeholder.jpg"
import UserImage from "../common/UserImage.jsx";

const VeterinarianCard = ({vet}) => {
    return (
        <Col key={vet.id} className="mb-4 xs={12}">
            <Accordion>
                <AccordionItem eventKey={"0"}>
                    <AccordionHeader>
                        <div className={"d-flex align-items-center"}>
                            <Link>
                                <UserImage
                                    userId={vet.id}
                                    userPhoto={vet.photo}
                                    placeholder={placeholder}
                                />
                            </Link>
                        </div>

                        <div className={"flex-grow-1 ml-3 px-5"}>
                            <CardTitle className={"title"}> Dr. {vet.firstName} {vet.lastName}

                            </CardTitle>

                            <CardTitle>
                                <h6> {vet.specialization} </h6>
                            </CardTitle>

                            <CardText className={"review rating-stars"}>
                                Reviews: Some stars
                            </CardText>

                            <Link to={`/book-appointment/${vet.id}/new-appointment`} className={"link"}>
                                Book An Appointment
                            </Link>
                        </div>
                    </AccordionHeader>

                    <AccordionBody>
                        <div>
                            <Link to={""} className={"link-2"}>See what people are saying about </Link>
                            <span className="margin-left-space"> Dr. {vet.firstName} </span>
                        </div>
                    </AccordionBody>
                </AccordionItem>
            </Accordion>
        </Col>
    );
};

export default VeterinarianCard;