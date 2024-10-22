import React, {useEffect, useState} from 'react';
import {Col, Container, Row} from "react-bootstrap";
import VeterinarianCard from "./VeterinarianCard.jsx";
import {getVeterinarians} from "./VeterinarianService.jsx";
import VeterinarianSearch from "./VeterinarianSearch.jsx";

const VeterinarianListing = () => {

    const [veterinarians, setVeterinarians] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        getVeterinarians()
            .then((data) => {
                setVeterinarians(data.data);
            }).catch((error) => {
            setErrorMessage(error);
        });
    }, []);

    if (veterinarians.length === 0) {
        return <p> No veterinarians found at this time! </p>;
    }

    const handleSearchResult = (searcresult) => {

    }


    return (
        <Container>

            <Row className="justify-content-center">
                <h2 className="text-center mb-4 mt-4">Meet Our Veterinarians</h2>
            </Row>

            <Row className={"justify-content-center"}>
                <Col md={4}>
                    <VeterinarianSearch onSearchResult={handleSearchResult()}></VeterinarianSearch>
                </Col>

                <Col md={7}>
                    {veterinarians.map((vet, index) => (
                        <VeterinarianCard key={index} vet={vet}/>
                    ))}
                </Col>
            </Row>

        </Container>

    );
};

export default VeterinarianListing;