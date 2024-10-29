import React, {useEffect, useState} from 'react';
import {Col, FormControl, FormGroup} from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal.jsx";
import {getPetColors} from "./PetService.js";

const PetColorSelector = ({value, onChange}) => {

    const [petColors, setPetColors] = useState([]);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        const fetchPetColors = async () => {
            try {
                const response = await getPetColors();
                console.log("The Colors: ", response.data)
                setPetColors(response.data);
            } catch (error) {
                console.error(error.response.data.message);
            }
        }
        fetchPetColors();
    }, [])

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
            <FormGroup as={Col} controlId={"petColor"}>
                <FormControl
                    as="select"
                    name="petColor"
                    value={value}
                    required
                    onChange={handleColorChange}>
                    <option value=''>Select Color</option>
                    <option value='add-new-item'>Add A New Color</option>
                    {petColors.map((color) => (
                        <option key={color} value={color}> {color} </option>
                    ))}
                </FormControl>
                <AddItemModal
                    show={showModal}
                    handleClose={() => setShowModal(false)}
                    handleSave={handleSaveNewItem}
                    itemLabel={'Color'}
                />
            </FormGroup>
        </React.Fragment>
    );
};

export default PetColorSelector;