#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <stdlib.h>

void createPrefix(char P[], int prefix[]);
void stringMatch(char T[], char P[], int prefix[]);

int main() {
    char text[100], filename[100], strpattern[100];
    char d;
	int prefix[100], c;

	FILE *fp;

    // text file
    printf("Enter File name: ");
    scanf("%s", filename);

    fp = fopen(filename, "r");
    if (fp != NULL) {

        // read the file
        while(fscanf(fp,"%c", &d)!=EOF){
            text[c]=d;
            c++;
        }

        printf("Text: \n");
        for(int i=0; i < c; i++)
            printf("%c", text[i]);

        printf("\nEnter Pattern: ");
        scanf("%s", strpattern);

        // create the prefix table
        createPrefix(strpattern, prefix);

        // find matches
        stringMatch(text, strpattern, prefix);
    }else
        printf("\nFile does not exist!\n");

    printf("\nThank you for using the program!");
    fclose(fp);
}

void createPrefix(char P[], int prefix[]){

    int a, b, length;

    length = strlen(P);
    prefix[0] = 0;
    a = 0;
    for (b = 1; b < length; b++) {
        while (a > 0 && (P[a] != P[b]))
            a = prefix[a];

        if (P[a] == P[b])
            a = a + 1;

        prefix[b] = a;
    }
}


void stringMatch(char T[], char P[], int prefix[]) {

    int i, j, k, match;

    i = 0; j = 0; k = 0;
    match = 0;

    int n = strlen(T) - 1;
    int m = strlen(P) - 1;

    while (n - k >= m) {
        while (j <= m && T[i] == P[j]){
            i++;
            j++;
        }

        if (j > m){
            printf("\nFound at index %d", k + 1);
            match++;
        }

        if (prefix[j - 1] > 0){
            k = i - prefix[j - 1];
        }else{
            if (i == k)
                i++;
            k = i;
        }

        if (j > 1)
            j = prefix[j - 1];
    }

    if(match != 0)
        printf("\nTotal number of matches: %d\n", match);
    else
        printf("\nNo matches found!\n");
}














