#include<stdio.h>


int encode_run(const char* line_run,unsigned length,char *buf,unsigned buf_len){

	if (length == 2){
		buf[0] = '1';
		return 1;
	}

	int count = 1;
	int index_buf = 0;

	for(int i = 1; i < length; i++){
		if(line_run[i] == line_run[i-1]){
			count++;
		}else{
			buf[index_buf] = (char) (count-0 + '0');
			index_buf++;
			count = 1;
		}
	}
	return 0;
}




int main(){
	char encoded_run[128];
	const char* line_run = "1122333334423";
	encode_run(line_run, 14, encoded_run, 128);
	printf("%s\n", encoded_run);
}