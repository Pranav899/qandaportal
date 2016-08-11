<#list questions as question>
		<tr >
						<td class = "tableheadrows"></td>
						<td class = "tableheadrows">${question.questionId}</td>
						<td class = "tableheadrows">${question.question}</td>
						<td class = "tableheadrows"><input type = "button" value = "${question.parentQuestionId}" class = "buttons"></td>
						<td class = "tableheadrows"><form method = "POST" action = "/qandaportal/rest/admin/confirm/${question.questionId}/${question.parentQuestionId}"><input type = "submit" value = "confirm" class = "buttons"></form></td>
						<td class = "tableheadrows"><form method = "POST" action = "/qandaportal/rest/admin/deny/${question.questionId}/${question.parentQuestionId}"><input type = "submit" value = "No" class = "buttons"></form></td>
		</tr>
</#list>