import React, {useState} from 'react';
import {FormControl, FormGroup} from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal.jsx";

const PetColorSelector = ({value, onChange}) => {

    const [petColors, setPetColors] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const handleColorChange = (e) => {
        if (e.target.value === "add-new-item") {
            setShowModal(true);
        } else {
            onChange(e);
        }
    };

    const handleSaveNewItem = (newItem) => {
        if (newItem && !petColors.includes(newItem)) {
            setPetColors([...petColors, newItem]);
            onChange({target: {name: "petColor", value: newItem}});
        }
    };


    return (
        <React.Fragment>
            <FormGroup>
                <FormControl
                    as="select"
                    name="petColor"
                    value={value}
                    required
                    onChange={handleColorChange}>
                    <option value=''>Select Color</option>
                    <option value='add-new-item'>Add A New Color</option>
                    <option value='White'>White</option>
                </FormControl>
                <AddItemModal
                    show={showModal}
                    handleClose={() => setShowModal(false)}
                    handleSave={handleSaveNewItem}
                    item={'Color'}
                />
            </FormGroup>
        </React.Fragment>
    );
};

export default PetColorSelector;