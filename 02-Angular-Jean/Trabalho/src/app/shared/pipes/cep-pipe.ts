import { mapToNumeric, formatToCEP } from './../helpers/utils.helper';
import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: 'cep' })
export class CepPipe implements PipeTransform {

  transform(value: string | null) {
    const str = mapToNumeric(value || '');
    if (str.length > 0) {
      return formatToCEP(str);
    }
    return ""
  }

}
