import React from 'react';
import {Button, Col, FormControl, FormGroup, OverlayTrigger, Row, Tooltip} from "react-bootstrap";
import PetColorSelector from "./PetColorSelector.jsx";
import PetTypeSelector from "./PetTypeSelector.jsx";
import PetBreedSelector from "./PetBreedSelector.jsx";
import {FaMinus} from "react-icons/fa";

const PetEntry = ({pet, index, removePet, canRemove, handleInputChange}) => {
    return (
        <fieldset>
            <legend className={'legend'}> {`Pet #${index + 1} details`} </legend>
            <FormGroup>
                <FormControl
                    type="text"
                    name={"petName"}
                    id={`petName -${index}`}
                    value={pet.petName}
                    placeholder={"Enter pet name"}
                    onChange={handleInputChange}
                    required
                />

                <FormControl
                    type="number"
                    name={"petAge"}
                    id={"petAge"}
                    value={pet.petAge}
                    placeholder={"Enter pet age"}
                    onChange={handleInputChange}
                    required
                />
            </FormGroup>

            <FormGroup as={Col} className={"mb-2"}>
                <PetColorSelector value={pet.petColor} onChange={handleInputChange}/>
            </FormGroup>

            <fieldset className={"field-set"}>
                <legend className={"legend"}>Pet Type and Breed</legend>
                <FormGroup as={Row} className={"mb-2 d-flex"}>
                    <Col>
                        <PetTypeSelector value={pet.petType} onChange={handleInputChange}/>
                    </Col>
                    <Col>
                        <PetBreedSelector
                            petType={pet.petType}
                            value={pet.petBreed}
                            onChange={handleInputChange}
                        />
                    </Col>
                </FormGroup>
            </fieldset>

            {canRemove && (
                <div className="d-flex justify-content-end mt-2">
                    <OverlayTrigger overlay={<Tooltip> remove pets </Tooltip>}>
                        <Button variant="danger" size="sm" onClick={() => removePet(index)}>
                            <FaMinus/>
                        </Button>
                    </OverlayTrigger>
                </div>
            )}
        </fieldset>
    );
};

export default PetEntry;