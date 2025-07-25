export type FormType = {
  partNumber: string,
  description: string,
}

export type DimensionType = {
  name: string;
  lowerLimit: number;
  upperLimit: number;
}

export type PartType = FormType & {
  id?: number
}

export type InspectionPlanType = {
  partNumber: string,
  version: string,
  dimensions: [DimensionType]
}