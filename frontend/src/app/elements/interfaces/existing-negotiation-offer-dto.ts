import { NegotiationStatus } from "../enums/negotiation-status";
import { NegotiationOfferDto } from "./negotiation-offer-dto";

export interface ExistingNegotiationOfferDto extends NegotiationOfferDto {
    status: NegotiationStatus;
}
