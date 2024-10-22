import {api} from "../utils/api.js";

export async function getVeterinarians() {
    // eslint-disable-next-line no-useless-catch
    try {
        const result = await api.get("/veterinarians/get-all-veterinarians");
        console.log("The result is: ", result);
        console.log("The result data is: ", result.data);
        return result.data;
    } catch (error) {
        throw error;
    }
}