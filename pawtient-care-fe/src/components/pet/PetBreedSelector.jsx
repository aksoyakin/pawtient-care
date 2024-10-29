import React, {useEffect, useState} from 'react';
import {Col, FormControl, FormGroup} from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal.jsx";
import {getPetBreeds, getPetTypes} from "./PetService.js";

const PetBreedSelector = ({petType, value, onChange}) => {

    const [petBreeds, setPetBreeds] = useState([]);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        if (petType) {
            const fetchPetBreeds = async () => {
                try {
                    const response = await getPetBreeds(petType);
                    console.log("The Breeds: ", response.data)
                    setPetBreeds(response.data);
                } catch (error) {
                    console.error(error.response.data.message);
                }
            };
            fetchPetBreeds();
        } else {
            setPetBreeds([]);
        }
    }, [petType]);


    const handleBreedChange = (e) => {
        if (e.target.value === "add-new-item") {
            setShowModal(true);
        } else {
            onChange(e);
        }
    };

    const handleSaveNewItem = (newItem) => {
        if (newItem && !petBreeds.includes(newItem)) {
            setPetBreeds([...petBreeds, newItem]);
            onChange({target: {name: "petBreed", value: newItem}});
        }
    }

    return (
        <React.Fragment>
            <FormGroup as={Col} controlId={"petBreed"}>
                <FormControl
                    as="select"
                    name="petBreed"
                    value={value}
                    required
                    onChange={handleBreedChange}>
                    <option value=''>Select Breed</option>
                    <option value='add-new-item'>Add A New Item</option>
                    {petBreeds.map((breed) => (
                        <option key={breed} value={breed}> {breed} </option>
                    ))}

                </FormControl>
                <AddItemModal
                    show={showModal}
                    handleClose={() => setShowModal(false)}
                    handleSave={handleSaveNewItem}
                    itemLabel={'Breed'}
                />
            </FormGroup>
        </React.Fragment>
    );
};

export default PetBreedSelector;