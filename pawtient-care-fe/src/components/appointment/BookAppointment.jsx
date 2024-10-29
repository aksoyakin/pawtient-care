import React, {useState} from 'react';
import {dateTimeFormatter} from "../utils/utilities.js";
import UseMessageAlerts from "../hooks/UseMessageAlerts.js";
import {useParams} from "react-router-dom";
import {
    Button,
    Card,
    CardBody,
    CardHeader,
    Col,
    Container,
    Form,
    FormGroup,
    FormLabel,
    OverlayTrigger,
    Row, Spinner, Tooltip
} from "react-bootstrap";
import DatePicker from "react-datepicker";
import PetEntry from "../pet/PetEntry.jsx";
import {FaPlus} from "react-icons/fa";
import {bookAppointment} from "./AppointmentService.js";
import AlertMessage from "../common/AlertMessage.jsx";
import ProcessSpinner from "../common/ProcessSpinner.jsx";

const BookAppointment = () => {

    const [isProcessing, setIsProcessing] = useState(false);

    const [formData, setFormData] = useState({
        appointmentDate: "",
        appointmentTime: "",
        reason: "",
        pets: [
            {
                petName: "",
                petType: "",
                petColor: "",
                petBreed: "",
                petAge: "",
            }
        ]
    });

    const {
        successMessage,
        setSuccessMessage,
        showSuccessAlert,
        setShowSuccessAlert,
        errorMessage,
        setErrorMessage,
        showErrorAlert,
        setShowErrorAlert
    } = UseMessageAlerts();

    const {recipientId} = useParams();

    const senderId = 3;


    const handleDateChange = (date) => {
        setFormData((prevState) => ({
            ...prevState, appointmentDate: date
        }))
    }

    const handleTimeChange = (time) => {
        setFormData((prevState) => ({
            ...prevState, appointmentTime: time
        }))
    }

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setFormData((prevState) => ({
            ...prevState, [name]: value
        }))
    }

    const handlePetChange = (index, e) => {
        const {name, value} = e.target;
        setFormData((prevState) => ({
            ...prevState,
            pets: prevState.pets.map((pet, idx) =>
                idx === index ? {...pet, [name]: value} : pet
            ),
        }));
    };

    const addPet = () => {
        const newPet = {
            petName: "",
            petType: "",
            petColor: "",
            petBreed: "",
            petAge: "",
        }
        setFormData((prevState) => ({
            ...prevState,
            pets: [...prevState.pets, newPet],
        }));
    }

    const removePet = (index, e) => {
        const filteredPets = formData.pets.filter((_, idx) => idx !== index);
        setFormData((prevState) => ({
            ...prevState,
            pets: filteredPets,
        }));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const {appointmentDate, appointmentTime} = formData;

        const {formattedDate, formattedTime} = dateTimeFormatter(appointmentDate, appointmentTime);


        const pets = formData.pets.map((pet) => ({
            name: pet.petName,
            type: pet.petType,
            breed: pet.petBreed,
            color: pet.petColor,
            age: pet.petAge,
        }))


        const request = {
            appointment: {
                appointmentDate: formattedDate,
                appointmentTime: formattedTime,
                reason: formData.reason,
            },
            pets: pets,
        };

        setIsProcessing(true)
        try {
            console.log("The appointment request: ", request)
            const response = await bookAppointment(senderId, recipientId, request);
            console.log("The appointment respone: ", response)
            setSuccessMessage(response.message);
            handleReset();
            setShowSuccessAlert(true);
        } catch (error) {
            console.log("The appointment error: ", error)
            setErrorMessage(error.response.data.message);
            setShowErrorAlert(true)
        } finally {
            
            setIsProcessing(false)
        }

    }
    const handleReset = () => {
        setFormData({
            appointmentDate: "",
            appointmentTime: "",
            reason: "",
            pets: [
                {
                    petName: "",
                    petType: "",
                    petColor: "",
                    petBreed: "",
                    petAge: "",
                },
            ],
        });
        setShowSuccessAlert(false);
        setShowErrorAlert(false);
    };

    return (
        <Container className={"mt-5"}>
            <Row className="justify-content-center">
                <Col lg={6} md={10} sm={12}>
                    <Form onSubmit={handleSubmit}>
                        <Card className={"shadow mb-5"}>
                            <CardHeader
                                as="h5"
                                className={"text-center"}> {" "}
                                Appointment Booking Form
                            </CardHeader>
                            <CardBody>
                                <fieldset className="field-set mb-4">
                                    <legend className={"text-center"}>
                                        Appointment Date and Time
                                    </legend>
                                    <FormGroup as={Row} className={"mb-4"}>
                                        <Col md={6}>
                                            <DatePicker
                                                selected={formData.appointmentDate}
                                                onChange={handleDateChange}
                                                dateFormat={"yyyy-MM-dd"}
                                                className={"form-control"}
                                                minDate={new Date()}
                                                placeholderText={"Choose A Date"}
                                                required
                                            />
                                        </Col>
                                        <Col sm={6}>
                                            <DatePicker
                                                selected={formData.appointmentTime}
                                                onChange={handleTimeChange}
                                                showTimeSelect
                                                showTimeSelectOnly
                                                timeIntervals={30}
                                                dateFormat="HH:mm"
                                                className={"form-control"}
                                                placeholderText={"Select time"}
                                                required
                                            />
                                        </Col>
                                    </FormGroup>
                                </fieldset>
                                <FormGroup className={"mb-4"}>
                                    <FormLabel>Reason For Appointment</FormLabel>
                                    <Form.Control
                                        as="textarea"
                                        rows={3}
                                        name="reason"
                                        onChange={handleInputChange}
                                        value={formData.reason}
                                        required/>
                                </FormGroup>
                                <h5 className={"text-center"}>Appointment Pet Information</h5>
                                {formData.pets.map((pet, index) => (
                                    <PetEntry
                                        key={index}
                                        pet={pet}
                                        index={index}
                                        handleInputChange={(e) => handlePetChange(index, e)}
                                        removePet={removePet}
                                        canRemove={formData.pets.length > 1}
                                    />
                                ))}

                                {
                                    showErrorAlert && (
                                        <AlertMessage
                                            type={"danger"}
                                            message={errorMessage}
                                        />
                                    )
                                }

                                {showSuccessAlert && (
                                    <AlertMessage
                                        type={"success"}
                                        message={successMessage}
                                    />
                                )}


                                <div className={"d-flex justify-content-center mb-3"}>
                                    <OverlayTrigger overlay={<Tooltip>Add Pets</Tooltip>}>
                                        <Button size={"sm"} onClick={addPet} className={"me-2"}>
                                            <FaPlus/>
                                        </Button>
                                    </OverlayTrigger>

                                    <Button
                                        type={"submit"}
                                        variant={"outline-primary"}
                                        size={"sm"}
                                        className={"me-2"}
                                        disabled={isProcessing}
                                    >
                                        {isProcessing ? (
                                            <ProcessSpinner message={"Booking appointment, please wait..."}/>
                                        ) : "Book Appointment"}

                                    </Button>

                                    <Button
                                        variant={"outline-info"}
                                        size={"sm"}
                                        onClick={handleReset}>
                                        Reset
                                    </Button>
                                </div>
                            </CardBody>
                        </Card>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default BookAppointment;