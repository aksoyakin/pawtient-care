import React, {useState} from 'react';
import {useAlertWithTimeout} from "../utils/utilities.js";

const UseMessageAlerts = () => {

    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [showErrorAlert, setShowErrorAlert] = useAlertWithTimeout();
    const [showSuccessAlert, setShowSuccessAlert] = useAlertWithTimeout();

    return {
        successMessage,
        setSuccessMessage,
        errorMessage,
        setErrorMessage,
        showSuccessAlert,
        setShowSuccessAlert,
        showErrorAlert,
        setShowErrorAlert,
    };
};

export default UseMessageAlerts;