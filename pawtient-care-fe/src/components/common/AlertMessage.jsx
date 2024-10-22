import React from 'react';
import {Alert} from "react-bootstrap";

const AlertMessage = ({type, message}) => {
    if (!message) return null;

    return (
        <Alert variant={type} dismissible>
            {message}
        </Alert>
    );
};

export default AlertMessage;
""