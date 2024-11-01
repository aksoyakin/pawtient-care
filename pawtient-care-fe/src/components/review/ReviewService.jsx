import {api} from "../utils/api.js";

export async function addReview(vetId, reviewerId, reviewData) {
    try {
        const response = await api.post(
            `reviews/submit-review?reviewerId=${reviewerId}&veterinarianId=${vetId}`,
            reviewData
        );
        return response.data;
    } catch (error) {
        throw error;
    }
}
