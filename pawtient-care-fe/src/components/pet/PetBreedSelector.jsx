import React, {useState} from 'react';
import {FormControl, FormGroup} from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal.jsx";

const PetBreedSelector = ({value, onChange}) => {

    const [petBreeds, setPetBreeds] = useState([]);
    const [showModal, setShowModal] = useState(false);

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
            <FormGroup>
                <FormControl
                    as="select"
                    name="petType"
                    value={value}
                    required
                    onChange={handleBreedChange}>
                    <option value=''>Select Breed</option>
                    <option value='add-new-item'>Add A New Item</option>
                    <option value='dog'>Dog</option>
                    <option value='cat'>Cat</option>
                </FormControl>
                <AddItemModal
                    show={showModal}
                    handleClose={() => setShowModal(false)}
                    handleSave={handleSaveNewItem}
                    item={'Breed'}
                />
            </FormGroup>
        </React.Fragment>
    );
};

export default PetBreedSelector;