import React, {useState} from 'react';
import format from 'date-fns/format';
import UseMessageAlerts from "../hooks/UseMessageAlerts.js";
import {Button, Col, Form, Row} from "react-bootstrap";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AlertMessage from "../common/AlertMessage.jsx";

const VeterinarianSearch = (onSearchResult) => {

    const [searchQuery, setSearchQuery] = useState({
            date: null,
            time: null,
            specialization: ""
        }
    );

    const [showDateTime, setShowDateTime] = useState(false);

    const {
        errorMessage,
        setErrorMessage,
        showErrorAlert,
        setShowErrorAlert
    } = UseMessageAlerts();

    const handleInputChange = (e) => {
        setSearchQuery({
            ...searchQuery,
            [e.target.name]: e.target.value
        })
    };

    const handleDateChange = (date) => {
        setSearchQuery({
            ...searchQuery,
            date
        })
    };

    const handleTimeChange = (time) => {
        setSearchQuery({
            ...searchQuery,
            time
        })
    };

    const handleDateTimeToggle = (e) => {
        const isChecked = e.target.checked;
        setShowDateTime(isChecked);
        if (!isChecked) {
            setSearchQuery({
                ...searchQuery,
                date: null,
                time: null,
            })
        }
    };

    const handleSearch = async (e) => {
        e.preventDefault();
        let searchParams = {specialization: searchQuery.specialization};

        if (searchQuery.date) {
            const formattedDate = format(searchQuery.date, "yyyy-MM-dd");
            searchParams.date = formattedDate;
        }

        if (searchQuery.time) {
            const formattedTime = format(searchQuery.time, "HH:mm");
            searchParams.time = formattedTime;
        }

        try {
            const response = await findAvailableVeterinarians(searchParams)
            onSearchResult(response.data)
            setShowErrorAlert(false)
        } catch (error) {
            setErrorMessage(error.message);
            setShowErrorAlert(true);
        }
    }

    const handleClearSearch = () => {
        setSearchQuery({
            date: null,
            time: null,
            specialization: ""
        })
        setShowDateTime(false)
        onSearchResult([])
    }

    return (
        <section className={"stickyFormContainer"}>
            <h3>Find a Veterinarian</h3>
            <Form onSubmit={handleSearch}>
                <Form.Group>
                    <Form.Label> Specialization </Form.Label>
                    <Form.Control as="select"
                                  name="specialization"
                                  value={searchQuery.specialization}
                                  onChange={handleInputChange}>
                        <option value="">Select Specialization</option>
                        <option value={"Surgeon"}>Surgeon</option>
                        <option value={"Urologist"}>Urologist</option>
                        <option value={"Other"}>Other</option>
                    </Form.Control>
                </Form.Group>

                <fieldset>
                    <Row className="mb-3">
                        <Col>
                            <Form.Group className={"mb-3"}>
                                <Form.Check
                                    type={"checkbox"}
                                    label={"Include Date and Time Availability"}
                                    checked={showDateTime}
                                    onChange={handleDateTimeToggle}
                                />
                            </Form.Group>
                            {showDateTime && (
                                <React.Fragment>
                                    <legend>Include Date and Time</legend>
                                    <Form.Group className={"mb-3"}>
                                        <Form.Label className={"searchText"}>Date</Form.Label>
                                        <DatePicker
                                            selected={searchQuery.date}
                                            onChange={handleDateChange}
                                            dateFormat={"yyyy-MM-dd"}
                                            minDate={new Date()}
                                            className={"form-control"}
                                            placeholderTexT={"Select date"}
                                        />
                                    </Form.Group>
                                    <Form.Group className={"mb-3"}>
                                        <Form.Label className={"searchText"}>Time</Form.Label>
                                        <DatePicker
                                            selected={searchQuery.time}
                                            onChange={handleTimeChange}
                                            showTimeSelect
                                            showTimeSelectOnly
                                            timeIntervals={30}
                                            dateFormat={"HH:mm"}
                                            className={"form-control"}
                                            placeholderTexT={"Select time"}
                                            required
                                        />
                                    </Form.Group>
                                </React.Fragment>
                            )}
                        </Col>
                    </Row>
                </fieldset>
                <div className="d-flex justify-content-center mb-4">
                    <Button type="submit" variant={"outline-primary"}>
                        Search
                    </Button>
                    <div className={"mx-2"}>
                        <Button
                            type={"button"}
                            variant={"outline-info"}
                            onClick={handleClearSearch}>
                            Clear Search
                        </Button>
                    </div>
                </div>
            </Form>
            <div>
                {showErrorAlert && (
                    <AlertMessage type={"danger"} message={errorMessage}/>
                )}
            </div>
        </section>
    );
};

export default VeterinarianSearch;