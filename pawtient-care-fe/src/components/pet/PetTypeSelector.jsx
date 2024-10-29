import React, {useEffect, useState} from 'react';
import {Col, FormControl, FormGroup} from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal.jsx";
import {getPetColors, getPetTypes} from "./PetService.js";

const PetTypeSelector = ({value, onChange}) => {

    const [petTypes, setPetTypes] = useState([]);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        const fetchPetTypes = async () => {
            try {
                const response = await getPetTypes();
                console.log("The Types: ", response.data)
                setPetTypes(response.data);
            } catch (error) {
                console.error(error.response.data.message);
            }
        }
        fetchPetTypes();
    }, [])

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
            <FormGroup as={Col} controlId={"petType"}>
                <FormControl
                    as="select"
                    name="petType"
                    value={value}
                    required
                    onChange={handleTypeChange}>
                    <option value=''>Select Type</option>
                    <option value='add-new-item'>Add A New Item</option>
                    {petTypes.map((type) => (
                        <option key={type} value={type}> {type} </option>
                    ))}
                </FormControl>
                <AddItemModal
                    show={showModal}
                    handleClose={() => setShowModal(false)}
                    handleSave={handleSaveNewItem}
                    itemLabel={'Type'}
                />
            </FormGroup>
        </React.Fragment>
    );
};

export default PetTypeSelector;