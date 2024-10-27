import React, {useState} from 'react';
import {FormControl, FormGroup} from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal.jsx";

const PetTypeSelector = ({value, onChange}) => {

    const [petTypes, setPetTypes] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const handleTypeChange = (e) => {
        if (e.target.value === "add-new-item") {
            setShowModal(true);
        } else {
            onChange(e);
        }
    };

    const handleSaveNewItem = (newItem) => {
        if (newItem && !petTypes.includes(newItem)) {
            setPetTypes([...petTypes, newItem]);
            onChange({target: {name: "petType", value: newItem}});
        }
    };

    return (
        <React.Fragment>
            <FormGroup>
                <FormControl
                    as="select"
                    name="petType"
                    value={value}
                    required
                    onChange={handleTypeChange}>
                    <option value=''>Select Type</option>
                    <option value='add-new-item'>Add A New Item</option>
                    <option value='dog'>Dog</option>
                    <option value='cat'>Cat</option>
                </FormControl>
                <AddItemModal
                    show={showModal}
                    handleClose={() => setShowModal(false)}
                    handleSave={handleSaveNewItem}
                    item={'Type'}
                />
            </FormGroup>
        </React.Fragment>
    );
};

export default PetTypeSelector;