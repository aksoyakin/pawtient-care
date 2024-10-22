import {useEffect, useState} from "react";


export const useAlertWithTimeout = (
    initialVisibility = false,
    duration = 10000
) => {
    const [isVisible, setIsVisible] = useState(initialVisibility);

    useEffect(() => {
        let timer;
        if (isVisible) {
            timer = setTimeout(() => {
                setIsVisible(false);
            }, duration);
        }
        return () => clearTimeout(timer);
    }, [isVisible, duration]);

    return [isVisible, setIsVisible];
};