import React from 'react';
import {Spinner} from "react-bootstrap";

const ProcessSpinner = ({size = "sm", animation = "border", message = ""}) => {
    return (
        <div className={"text-center"}>
            <Spinner
                as="span"
                animation={animation}
                size={size}
                role="status"
                aria-hidden="true"
            />
            {message && <span className="sr-only">{message}</span>}

        </div>
    );
};

export default ProcessSpinner;